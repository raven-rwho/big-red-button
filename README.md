Big Red Button
===================

This is  a fun project which I'm implemented to help our QA.
It is a small app that triggers a Jenkins Job - normally the build job of our app .

Just add the necessary settings:

 
> **Settings:**

> - Jenkins Base Url - something like https://jenkins.qa.ubuntu.com/
> - Jenkins Job URL - something like /view/Precise/view/All Precise/job/precise-adt-apport
> - Jenkins User Name - well your username
> - your jenkins api token - can be found here
```
http://<jenkins-server>/user/<username>/configure

```

----------

#### <i class="icon-folder-open"></i>Libraries

-  [Dagger 2][1]
-  [Retrofit][2]
-  [retrolambda][3]
-  [timber][4]
 

  [1]: http://google.github.io/dagger/
  [2]: http://square.github.io/retrofit/
  [3]: https://github.com/orfjackal/retrolambda
  [4]: https://github.com/JakeWharton/timber

