pipeline {
  agent any
  
  stages {
    stage('Build') {
      steps {
        git 'https://github.com/gkojiyamada/AC2Leles2.git'
        bat 'mvn clean package'
        bat 'docker system prune -a --volumes -f'
        bat 'docker build . --tag integration/integration-prod'
      }
      
      post {
        success {
          junit '**/target/surefire-reports/TEST-*.xml'
          archiveArtifacts 'target/praticandoAPI-0.0.1-SNAPSHOT/WEB-INF/lib/*.jar'
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
