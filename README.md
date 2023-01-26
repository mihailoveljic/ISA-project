# ISA-project

## Prvo pokretanje

0. Instalirati Docker Desktop
1. Pozicionirati se unutar projekta u terminalu
2. U terminal uneti: `docker compose up`
3. Treba da se pojavi 7 kontejnera

- Ako je potrebno, u application.properties (isa-backend) promeniti username i password da odgovara bazi.


## PgAdmin - pristup bazi kroz web browser

0. Upaliti docker container-e
1. Otići na `http://localhost:5051/`
2. Ulogovati se sa:
`email: user@email.com
password: user`
3. Add new server
4. U general tab-u uneti `Name: blood_bank_db_isa`
5. U connection tab uneti `Host name/Adress: blood_bank_db_isa`, `username: user` i `password: user`

## Keycloak
0. Upaliti docker container-e
1. Otići na `http://localhost:28080/`
2. Ulogovati se sa: `username: admin password: admin`
3. Kreirati novi realm
4. Kreirati korisnika za svaku ulogu

## Frontend
0. Pokrenuti projekat u Visual Studio Code - u
1. U terminalu uneti `npm i` pa `ng serve`




