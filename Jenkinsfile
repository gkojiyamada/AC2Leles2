pipeline {
  agent any
  
  tools {
    maven "M3"
  }
  stages {
    stage('Build') {
      steps {
        git 'https://github.com/HGRon/ci-cd-integration.git'
        bat 'mvn clean package'
        bat 'docker system prune -a --volumes -f'
        bat 'docker build . --tag integration/integration-prod'
      }
      
      post {
        success {
          junit '**/target/surefire-reports/TEST-*.xml'
          archiveArtifacts 'target/*.jar'
          step( [ $class: 'JacocoPublisher' ] )
        }
      }
    }
    stage('Deploy') {
      steps {
        timeout(time: 15, unit: "MINUTES") {
            input message: 'Aprovar o deploy?', ok: 'Sim'
        }

        echo "Iniciando deploy"
        bat 'docker run -d integration/integration-prod'
      }
    }
  }

}
