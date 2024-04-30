curl --location 'localhost:8080/api/sites' \
--header 'Content-Type: application/json' \
--data '{
    "name": "name",
    "location": "location",
    "description": "description"
}'

curl --location --request POST 'localhost:8080/api/sites' \
--form 'name="name"' \
--form 'location="location"' \
--form 'description="description"' \
--form 'imageFile=@"/home/sellami/Pictures/Screenshots/Screenshot from 2024-03-26 22-21-39.png"'

curl --location --request POST 'localhost:8080/api/sites' \
--form 'name="name"' \
--form 'location="location"' \
--form 'description="description"'

curl --location 'localhost:8080/api/sites' \
--form 'imageFile=@"/home/sellami/Untitled.ipynb"'

curl --location --request DELETE 'localhost:8080/api/sites/202'

curl -X POST \
  http://localhost:8080/api/sites \
  -H 'Content-Type: multipart/form-data' \
  -F 'site={"name":"Example Site","location":"Example Location","description":"Example Description"}' \
  -F 'imageFile=@/home/sellami/Untitled.ipynb'

ALTER TABLE sites
DROP COLUMN img;

ALTER TABLE sites
ALTER COLUMN description varchar(1020);