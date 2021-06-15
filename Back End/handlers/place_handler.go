package handlers

import (
	"encoding/json"
	"net/http"
	"travelBD/models"
	"travelBD/services"

	"github.com/go-chi/chi/v5"
)

type IPlaceHandler interface{ IHandler }

type PlaceHandler struct {
	placeService services.IPlaceService
}

func NewPlaceHandle(
	placeService services.IPlaceService,
) IPlaceHandler {
	return &PlaceHandler{
		placeService: placeService,
	}
}

func (h *PlaceHandler) Handle(router chi.Router) {
	router.Post("/", h.createPlace)
	router.Get("/", h.getAllPlace)
	router.Get("/{id}", h.getPlaceByID)
}

func (h *PlaceHandler) createPlace(w http.ResponseWriter, r *http.Request) {
	place := models.Place{}
	parsingErr := json.NewDecoder(r.Body).Decode(&place)
	if parsingErr != nil {
		BadRequest(w, parsingErr)
		return
	}
	createdPlace, err := h.placeService.Create(&place)
	if err != nil {
		BadRequest(w, err)
		return
	}
	Created(w, createdPlace)
}
func (h *PlaceHandler) getAllPlace(w http.ResponseWriter, r *http.Request)  {}
func (h *PlaceHandler) getPlaceByID(w http.ResponseWriter, r *http.Request) {}
