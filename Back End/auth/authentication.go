package auth

import (
	"encoding/json"
	"errors"
	"net/http"
	"strconv"
	"strings"
	"time"
	"travelBD/models"

	"github.com/dgrijalva/jwt-go"
)

type IAuth interface {
}

const (
	secret          = "TraVelBdSecR3t"
	KeyUserID       = "id"
	KeyTokenExpired = "exp"
)

type Auth struct {
	Secret string
}

func NewAuth(auth *Auth) IAuth {
	return &Auth{
		Secret: secret,
	}
}

func (auth *Auth) GenerateToken(user *models.UserDomain) (string, error) {
	token := jwt.New(jwt.SigningMethodES256)
	claims := token.Claims.(jwt.MapClaims)
	claims[KeyUserID] = user.ID
	claims[KeyTokenExpired] = time.Now().Add(time.Minute * 30).Unix()
	signedToken, err := token.SignedString([]byte(auth.Secret))
	if err != nil {
		return "", err
	}
	return signedToken, nil
}

func (auth *Auth) Authentication(handler http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		w.Header().Set("content-type", "application/json")
		extractToken := func() string {
			bearerToken := r.Header.Get("Authorization")
			strArr := strings.Split(bearerToken, " ")
			if len(strArr) == 2 {
				return strArr[1]
			}
			return ""
		}
		if r.Header["Authorization"] != nil {
			tokenString := extractToken()
			token, err := jwt.Parse(tokenString, func(t *jwt.Token) (interface{}, error) {
				if _, ok := t.Method.(*jwt.SigningMethodHMAC); !ok {
					return nil, errors.New("Invalid authorization token")

				}
				return []byte(auth.Secret), nil
			})
			if err != nil {
				w.WriteHeader(http.StatusUnauthorized)
				json.NewEncoder(w).Encode(err)
				return
			}
			if token.Valid {
				claims := token.Claims.(jwt.MapClaims)
				r.Header.Set(KeyUserID, strconv.FormatFloat((claims[KeyUserID]).(float64), 'f', 0, 64))
				handler.ServeHTTP(w, r)
			} else {
				w.WriteHeader(http.StatusUnauthorized)
				json.NewEncoder(w).Encode(errors.New("Invalid authorization token"))
				// return
			}
		} else {
			w.WriteHeader(http.StatusUnauthorized)
			json.NewEncoder(w).Encode(errors.New("invalid authorization token"))
			// return/
		}
	})
}
