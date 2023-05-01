package services

import (
	"errors"
	"travelBD/models"
	"travelBD/repositories"
)

type IUserService interface {
	Create(user *models.UserDomain) (*models.UserDomain, error)
	GetAll() ([]*models.UserDomain, error)
	GetByID(id uint) (*models.UserDomain, error)
	Update(id uint, userData *models.UserDomain) (*models.UserDomain, error)
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

func (h *UserService) GetByID(id uint) (*models.UserDomain, error) {
	return h.userRepository.GetByID(id)
}

func (h *UserService) Update(id uint, userData *models.UserDomain) (*models.UserDomain, error) {
	userToUpdate, err := h.GetByID(id)
	if err != nil {
		return nil, errors.New("user not found")
	}
	userToUpdate.Email = userData.Email
	userToUpdate.Name = userData.Name
	userToUpdate.UserName = userData.UserName
	// userToUpdate.
	updatedUser, err := h.userRepository.Update(userToUpdate)
	if err != nil {
		return nil, errors.New("failed to update user")
	}
	return updatedUser, nil
}
