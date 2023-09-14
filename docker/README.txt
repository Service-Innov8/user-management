- to build docker image
    docker build -t user-mngmt . -f docker/Dockerfile

- to make the tag and push it to dockerhub
    docker tag user-mngmt karagp/user-management
    docker push karagp/user-management


