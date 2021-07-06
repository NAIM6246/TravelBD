package models

type Place struct {
	ID          int
	Name        string
	Description string
	District    string
	Category    string
	AuthorID    int
}

func PlaceTableName() string {
	return "places"
}
