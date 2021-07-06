package services

import (
	"travelBD/models"
	"travelBD/repositories"
)

type IUserService interface {
	Create(user *models.UserDomain) (*models.UserDomain, error)
	GetAll() ([]*models.UserDomain, error)
	GetByID(id int) (*models.UserDomain, error)
}

type UserService struct {
	userRepository repositories.IUserRepository
}

func NewUserService(
	userRepository repositories.IUserRepository,
) IUserService {
	return &UserService{
		userRepository: userRepository,
	}
}

func (h *UserService) Create(user *models.UserDomain) (*models.UserDomain, error) {
	createdUser, err := h.userRepository.Create(user)
	if err != nil {
		return nil, err
	}
	return createdUser, nil
}
func (h *UserService) GetAll() ([]*models.UserDomain, error) {
	return h.userRepository.GetAll()
}

func (h *UserService) GetByID(id int) (*models.UserDomain, error) {
	return h.userRepository.GetByID(id)
}
