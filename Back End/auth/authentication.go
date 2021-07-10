package auth

import "github.com/go-chi/chi/v5"

type IAuth interface {
}

type Auth struct{}

func NewAuth() IAuth {
	return &Auth{}
}

func (h *Auth) Handle(router chi.Router) {}
