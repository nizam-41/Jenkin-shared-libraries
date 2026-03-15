def call(string url, string branch){
     git url: "${url}", branch: "${branch}"
     echo "Code cloing Successfull"
}
