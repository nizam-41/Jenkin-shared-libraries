def call(Map config = [:]) {

    def imageName = config.imageName ?: error("Image name is required")
    def imageTag = config.imageTag ?: "latest"
    def credentials = config.credentials ?: "DockerHubCred"

    echo "Pushing Docker image: ${imageName}:${imageTag}"

    withCredentials([usernamePassword(
        credentialsId: credentials,
        usernameVariable: "dockerHubUser",
        passwordVariable: "dockerHubPass"
    )]) {

        sh """
        echo ${dockerHubPass} | docker login -u ${dockerHubUser} --password-stdin
        docker tag ${imageName}:${imageTag} ${dockerHubUser}/${imageName}:${imageTag}
        docker push ${dockerHubUser}/${imageName}:${imageTag}
        """
    }
}
