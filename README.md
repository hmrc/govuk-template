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

This library has different releases for Play 2.8, Play 2.9, and Play 3.0.

Add the following to your SBT build (please note the differences from Play 2.9 onwards):

* Play 2.8: `libraryDependencies += "uk.gov.hmrc" %% "govuk-template" % "5.80.0-play-28"`
* Play 2.9: `libraryDependencies += "uk.gov.hmrc" %% "govuk-template-play-29" % "6.0.0"`

## License ##

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").

