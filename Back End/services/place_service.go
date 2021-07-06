package services

import (
	"travelBD/models"
	"travelBD/repositories"
)

type IPlaceService interface {
	Create(newPlace *models.Place) (*models.Place, error)
	GetAll() ([]*models.Place, error)
	GetPlaceOfDistrict(district string) ([]*models.Place, error)
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

func (h *PlaceService) GetAll() ([]*models.Place, error) {
	return h.placeRepository.GetAll()
}

func (h *PlaceService) GetPlaceOfDistrict(district string) ([]*models.Place, error) {
	return h.placeRepository.GetPlaceOfDistrict(district)
}
