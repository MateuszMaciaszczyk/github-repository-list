# GitHub Repository List

A Spring Boot application that fetches and displays GitHub repositories of a given user. The application provides both a web interface and a JSON API to view repository details and their branches.

## Features

- **Web Interface**: A user-friendly web form to input a GitHub username and view the repositories and their branches.
- **JSON API**: A RESTful endpoint to fetch repository details in JSON format.
- **Error Handling**: Proper handling of cases where a user is not found on GitHub.

## Technologies

- **Spring Boot**: Framework for building the web application.
- **Thymeleaf**: Templating engine used for rendering HTML.
- **Bootstrap**: CSS framework used for styling the web interface.
- **RestTemplate**: For making HTTP requests to GitHub's API.
- **JUnit & Mockito**: For testing the service and controller layers.

## Getting Started

### Prerequisites

- Java 21 or higher
- Spring Boot 3 or higher
- Maven 3.6 or higher

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/github-repository-list.git
  ```

2. **Navigate to the Project Directory**

   ```bash
   cd github-repository-list
  ```

3. **Build the Project**

   ```bash
   mvn clean install
  ```

4. **Run the Application**

   ```bash
   mvn spring-boot:run
  ```

The application will start on http://localhost:8080.

## Usage

1. **Open the Web Interface**

   Navigate to [http://localhost:8080](http://localhost:8080) in your browser.
   
3. **Fetch Repositories**

   - Enter a GitHub username in the input field.
   - Click the "Get Repositories" button.

   The page will display the repositories and their branches for the specified user.

4. **View JSON Data**

   - After fetching repositories, click on the "View JSON" link to see the repository data in JSON format.

## API Endpoints

- **Get Repositories (JSON)**

  `GET /repos/json?username={username}`

  - **Parameters**: 
    - `username` (required) - The GitHub username to fetch repositories for.
  - **Response**: JSON array of repositories with their details. Each repository includes:
    - `name`: The name of the repository.
    - `ownerLogin`: The GitHub username of the repository owner.
    - `branches`: A list of branches in the repository. Each branch includes:
      - `name`: The name of the branch.
      - `commitSha`: The SHA of the latest commit on the branch.
