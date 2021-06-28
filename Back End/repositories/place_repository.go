package repositories

import (
	"travelBD/conn"
	"travelBD/models"
)

type IPlaceRepository interface {
	Create(newPlace *models.Place) (*models.Place, error)
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
