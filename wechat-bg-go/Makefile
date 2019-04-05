# Go parameters
GOPATH=$(CURDIR)
GOCMD=go
VERSION=1.0
GOBUILD=$(GOCMD) build
GOCLEAN=$(GOCMD) clean
GOTEST=$(GOCMD) test
GOGET=$(GOCMD) get
BINARY_NAME=wechat-service
BINARY_UNIX=$(BINARY_NAME)_unix

MAIN=./src/wechat/mp/app.go

all: deps build
build:
	@CGO_ENABLED=0 GOOS=linux GOARCH=amd64 $(GOBUILD) -o $(BINARY_UNIX) -v $(MAIN)
test:
	$(GOTEST) -v ./...
clean:
	$(GOCLEAN)
	rm -f $(BINARY_NAME)
	rm -f $(BINARY_UNIX)
run:
	$(GOBUILD) -o $(BINARY_NAME) -v $(MAIN)
	./$(BINARY_NAME)
deps:
	@cd src && glide install
up:
	@cd src && glide up