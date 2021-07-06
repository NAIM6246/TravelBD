package repositories

import (
	"travelBD/conn"
	"travelBD/models"
)

type IPlaceRepository interface {
	Create(newPlace *models.Place) (*models.Place, error)
	GetAll() ([]*models.Place, error)
	GetByID(id uint) (*models.Place, error)
	GetPlaceOfDistrict(district string) ([]*models.Place, error)
}

type PlaceRepository struct {
	*BaseRepository
}

func NewPlaceRepository(db *conn.DB) IPlaceRepository {
	return &PlaceRepository{
		&BaseRepository{
			db: db.Table(models.PlaceTableName()),
		},
	}
}

func (repo *PlaceRepository) Create(newPlace *models.Place) (*models.Place, error) {
	if err := repo.db.Create(&newPlace).Error; err != nil {
		return nil, err
	}
	return newPlace, nil
}

func (repo *PlaceRepository) GetAll() ([]*models.Place, error) {
	var places []*models.Place
	if err := repo.db.Find(&places).Error; err != nil {
		return nil, err
	}
	return places, nil
}

func (repo *PlaceRepository) GetByID(id uint) (*models.Place, error) {
	var place models.Place
	if err := repo.db.Where("id=?", id).First(&place).Error; err != nil {
		return nil, err
	}
	return &place, nil
}

func (repo *PlaceRepository) GetPlaceOfDistrict(district string) ([]*models.Place, error) {
	var places []*models.Place
	if err := repo.db.Where("district=?", district).Find(&places).Error; err != nil {
		return nil, err
	}
	return places, nil
}
