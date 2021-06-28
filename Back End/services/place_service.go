package services

import (
	"travelBD/models"
	"travelBD/repositories"
)

type IPlaceService interface {
	Create(newPlace *models.Place) (*models.Place, error)
}

type PlaceService struct {
	placeRepository repositories.IPlaceRepository
}

func NewPlaceService(
	placeRepository repositories.IPlaceRepository,
) IPlaceService {
	return &PlaceService{
		placeRepository: placeRepository,
	}
}

func (h *PlaceService) Create(newPlace *models.Place) (*models.Place, error) {
	return h.placeRepository.Create(newPlace)
}
