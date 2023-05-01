package services

import (
	"travelBD/models"
	"travelBD/repositories"
)

type IPlaceService interface {
	Create(newPlace *models.Place) (*models.Place, error)
	GetAll() ([]*models.Place, error)
	GetByID(id uint) (*models.Place, error)
	GetPlaceOfDistrict(district string) ([]*models.Place, error)
	Update(id uint, place *models.Place) (*models.Place, error)
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

func (h *PlaceService) GetByID(id uint) (*models.Place, error) {
	return h.placeRepository.GetByID(id)
}

func (h *PlaceService) GetPlaceOfDistrict(district string) ([]*models.Place, error) {
	return h.placeRepository.GetPlaceOfDistrict(district)
}

func (h *PlaceService) Update(id uint, place *models.Place) (*models.Place, error) {
	placeToUpdate, err := h.GetByID(id)
	if err != nil {
		return nil, err
	}
	placeToUpdate.Category = place.Category
	placeToUpdate.Description = place.Description
	placeToUpdate.District = place.District
	placeToUpdate.Name = place.Name
	return h.placeRepository.Update(placeToUpdate)
}
