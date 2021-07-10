package handlers

import (
	"net/http"

	"github.com/go-chi/chi/v5"
)

type IProfileHandler interface{ IHandler }

type ProfileHandler struct {
}

func NewProfileHandler() IProfileHandler {
	return &ProfileHandler{}
}

func (h *ProfileHandler) Handle(router chi.Router) {
	router.Get("/login", h.login)
}

func (h *ProfileHandler) login(w http.ResponseWriter, r *http.Request) {

}
