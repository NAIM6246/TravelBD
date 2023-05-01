package system

import (
	"fmt"
	"net/http"
	"travelBD/auth"
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

	container.Provide(auth.NewAuth)

	//config
	container.Provide(config.NewDBConfig)
	container.Provide(conn.ConnectDB)

	//handlers
	container.Provide(handlers.NewUserHandler)
	container.Provide(handlers.NewPlaceHandle)
	container.Provide(handlers.NewAuthHandler)

	//services
	container.Provide(services.NewUserService)
	container.Provide(services.NewPlaceService)
	container.Provide(services.NewAuthService)

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
	authHandler  handlers.IAuthHandler
	dbContext    *conn.DB
}

func NewServer(
	userHandler handlers.IUserHandler,
	placeHandler handlers.IPlaceHandler,
	authHandler handlers.IAuthHandler,
	dbContext *conn.DB,
) *Server {
	return &Server{
		router:       chi.NewRouter(),
		userHandler:  userHandler,
		placeHandler: placeHandler,
		authHandler:  authHandler,
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
	s.router.Route("/auth/", s.authHandler.Handle)
}

func (s *Server) dispose() {
}
