govuk-template
==============

[![Build Status](https://travis-ci.org/hmrc/govuk-template.svg)](https://travis-ci.org/hmrc/govuk-template) [ ![Download](https://api.bintray.com/packages/hmrc/releases/govuk-template/images/download.svg) ](https://bintray.com/hmrc/releases/govuk-template/_latestVersion)

Simple library wrapping the [GDS GOV.UK template](https://github.com/alphagov/govuk_template_play).

Note that currently we forked the GDS files. This solution is temporary and in future we will import the GDS artifact directly.

### Using the template

In order to use the template, you have to add this line to your application `routes` file

```scala
->     /template                       template.Routes
```

### Installing

Add the following to your SBT build:
```scala
resolvers += Resolver.bintrayRepo("hmrc", "releases")

libraryDependencies += "uk.gov.hmrc" % "govuk-template" % "[INSERT-VERSION]"
```

## License ##

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").

