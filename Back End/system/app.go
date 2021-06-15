package system

import (
	"fmt"
	"net/http"
	"travelBD/config"
	"travelBD/conn"
	"travelBD/handlers"
	"travelBD/repositories"
	"travelBD/services"

	"github.com/go-chi/chi/v5"
	"go.uber.org/dig"
)

const port = ":8000"

func buildContainer() *dig.Container {
	container := dig.New()

	//config
	container.Provide(config.NewDBConfig)
	container.Provide(conn.ConnectDB)

	//handlers
	container.Provide(handlers.NewUserHandler)
	container.Provide(handlers.NewPlaceHandle)

	//services
	container.Provide(services.NewUserService)
	container.Provide(services.NewPlaceService)

	//repositories
	container.Provide(repositories.NewUserRepository)
	container.Provide(repositories.NewPlaceRepository)

	container.Provide(NewServer)
	return container
}

type System struct{}

func NewSystem() {
	container := buildContainer()
	err := container.Invoke(func(server *Server) {
		server.run()
	})
	if err != nil {
		panic(err)
	}
}

type Server struct {
	router       chi.Router
	userHandler  handlers.IUserHandler
	placeHandler handlers.IPlaceHandler
	dbContext    *conn.DB
}

func NewServer(
	userHandler handlers.IUserHandler,
	placeHandler handlers.IPlaceHandler,
	dbContext *conn.DB,
) *Server {
	return &Server{
		router:       chi.NewRouter(),
		userHandler:  userHandler,
		placeHandler: placeHandler,
		dbContext:    dbContext,
	}
}

func (s *Server) run() {
	s.dbContext.Migration()
	s.mapHandlers()
	defer s.dispose()
	fmt.Println("Serving on port ", port)
	http.ListenAndServe(port, s.router)
}

func (s *Server) mapHandlers() {
	s.router.Route("/users", s.userHandler.Handle)
	s.router.Route("/places/", s.placeHandler.Handle)
}

func (s *Server) dispose() {
}
