package repositories

import (
	"travelBD/conn"
	"travelBD/models"
)

type IUserRepository interface {
	Create(user *models.UserDomain) (*models.UserDomain, error)
	GetAll() ([]*models.UserDomain, error)
	GetByID(id uint) (*models.UserDomain, error)
	GetByFilter(filter interface{}, arg ...interface{}) (*models.UserDomain, error)
	Update(updatedUser *models.UserDomain) (*models.UserDomain, error)
}

type UserRepository struct {
	*BaseRepository
}

func NewUserRepository(db *conn.DB) IUserRepository {
	return &UserRepository{
		&BaseRepository{
			db: db.Table(models.UserTableName()),
		},
	}
}

func (repo *UserRepository) Create(user *models.UserDomain) (*models.UserDomain, error) {
	if err := repo.db.Create(&user).Error; err != nil {
		return nil, err
	}
	return user, nil
}

func (repo *UserRepository) GetAll() ([]*models.UserDomain, error) {
	var users []*models.UserDomain
	if err := repo.db.Find(&users).Error; err != nil {
		return nil, err
	}
	return users, nil
}

func (repo *UserRepository) GetByFilter(filter interface{}, arg ...interface{}) (*models.UserDomain, error) {
	var user models.UserDomain
	if err := repo.db.Where(filter, arg...).First(&user).Error; err != nil {
		return nil, err
	}
	return &user, nil
}

func (repo *UserRepository) GetByID(id uint) (*models.UserDomain, error) {
	var user models.UserDomain
	if err := repo.db.Where("id=?", id).First(&user).Error; err != nil {
		return nil, err
	}
	return &user, nil
}

func (repo *UserRepository) Update(updatedUser *models.UserDomain) (*models.UserDomain, error) {
	if err := repo.db.Save(updatedUser).Error; err != nil {
		return nil, err
	}
	return updatedUser, nil
}
