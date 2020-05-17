# reval-leadex

The automated test-cases writen on *Kotlin* and based on: *Junit5, Leadium, Selenium, Selenide, Selenoid*.

For build and run uses: *Gradle*.

For reporting results of tests uses: *Allure2*.

## how to start
- Install Docker
- Pull Docker images:
```
  $ docker pull selenoid/video-recorder
  $ docker pull selenoid/vnc:chrome_65.0
```
- Create Docker network:
```
  $ docker network create \
   --driver=bridge \
   --subnet=172.28.0.0/16 \
   --ip-range=172.28.5.0/24 \
   --gateway=172.28.5.254 \
   bridge1
 ```
- Download [Selenoid](https://github.com/aerokube/selenoid/releases)

- Run Selenoid UI:
```
  $ ./selenoid-ui -listen :8080
```
- Run Selenoid Hub:
```
  $ ./selenoid -limit 5 -timeout 2m -container-network bridge1
```

- Run Tests (from project directory):
```
$ ./gradlew -Dhost=DESKTOP-542K33H -Dparallel=true :example:test --tests example.suite.ExampleSuite -i
```
- Download [Allure](https://github.com/allure-framework/allure2/releases)

- Generate report:
```
  $ ./allure serve /path/to/project_dir/source/apps/module_name/build/allure-results
```
