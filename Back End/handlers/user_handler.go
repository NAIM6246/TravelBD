package handlers

import (
	"encoding/json"
	"errors"
	"fmt"
	"net/http"
	"travelBD/models"
	"travelBD/services"

	"github.com/go-chi/chi/v5"
)

type IUserHandler interface{ IHandler }

type UserHandler struct {
	userService services.IUserService
}

func NewUserHandler(userService services.IUserService) IUserHandler {
	return &UserHandler{
		userService: userService,
	}
}

func (h *UserHandler) Handle(router chi.Router) {
	router.Get("/", h.getAllUser)
	router.Post("/", h.createUser)
}

func (h *UserHandler) getAllUser(w http.ResponseWriter, r *http.Request) {
	users, err := h.userService.GetAll()
	if err != nil || len(users) == 0 {
		NotFound(w, errors.New("no user"))
		return
	}
	Ok(w, users)
}
func (h *UserHandler) createUser(w http.ResponseWriter, r *http.Request) {
	user := models.UserDomain{}
	fmt.Println("hi")
	parSingErr := json.NewDecoder(r.Body).Decode(&user)
	if parSingErr != nil {
		BadRequest(w, parSingErr)
		return
	}
	fmt.Println(user)
	createdUser, err := h.userService.Create(&user)
	if err != nil {
		BadRequest(w, err)
		return
	}
	Created(w, createdUser)
}
