pipeline {
  agent any
  environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }
  stages {
    stage("verify tooling") {
      steps {
        bat '''
          docker version
          docker info
          docker compose version
          curl --version
          '''
      }
    }
    stage('Prune Docker data') {
      steps {
        bat 'docker system prune -a --volumes -f'
      }
    }
    stage('Start container') {
      steps {
        bat 'docker compose up -d --no-color --wait'
        bat 'docker compose ps'
      }
    }
    stage('Run tests against the container') {
      steps {
        bat 'curl http://localhost:9090'
      }
    }
     stage('Push image') {
        withDockerRegistry([ credentialsId: "dockerhub", url: "" ]) {
        bat "docker push devopsglobalmedia/teamcitydocker:build"
        }
  
        stage('Build and push image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                        def image = docker.build("my-image")
                        image.push()
                    }
                }
            }
        }
  }

}
