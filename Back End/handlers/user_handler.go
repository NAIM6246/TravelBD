package handlers

import (
	"encoding/json"
	"errors"
	"fmt"
	"net/http"
	"travelBD/handlers/param"
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
	router.Get("/{userID}", h.getUserByID)
	router.Get("/{userID}", h.updateUser)
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
	createdUser, err := h.userService.Create(&user)
	if err != nil {
		BadRequest(w, err)
		return
	}
	fmt.Println(user)
	Created(w, createdUser)
}

func (h *UserHandler) getUserByID(w http.ResponseWriter, r *http.Request) {
	id := param.UInt(r, "userID")
	user, err := h.userService.GetByID(id)
	if err != nil {
		NotFound(w, err)
		return
	}
	fmt.Println("asdf")
	Ok(w, user)
}

func (h *UserHandler) updateUser(w http.ResponseWriter, r *http.Request) {
	id := param.UInt(r, "userID")
	userData := models.UserDomain{}
	parsingErr := json.NewDecoder(r.Body).Decode(&userData)
	if parsingErr != nil {
		BadRequest(w, parsingErr)
		return
	}
	updatedUser, err := h.userService.Update(id, &userData)
	if err != nil {
		BadRequest(w, err)
		return
	}
	Ok(w, updatedUser)
}
