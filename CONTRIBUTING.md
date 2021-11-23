Thank you for your interest in helping us to make the SQL Statement Builder better!

# Goal of This Document
This document aims at answering all the questions potential contributors to the SQL Statement Builder might have before feeling ready to get started.

# What This Document is not
If you are looking for general information about what the SQL Statement Builder is, please make sure to check out the [ReadMe](./README.md) that comes with the project.

# What Kind of Contributions can I Make?

## Contributing Code
If you are a programmer and want to help us improve the implementation, test cases or design of OFT please create a branch of the current `develop` branch of the project using [git](https://git-scm.com/) and make your changes on that branch.

Then please create a pull request and ask for a review by one of the core team members. The reviewers will either ask you to work in review findings or if there are none, merge your branch.

## Testing
We are happy if you test the SQL Statement Builder! While we do a great deal of testing ourselves, we want OFT to be as portable as possible. So it is especially helpful for us if you test on a platform that we don't have.

If you find a bug, please let us know by writing an [issue ticket](https://github.com/EXASOL/sql-statement-builder/issues/new?template=Bug_report.md). There is a template for bug tickets that helps you provide all the information that we need to reproduce and tackle the bug you found.

If you are a programmer, a code contribution in form of an automatic unit test case would be most appreciated, since this will make reproduction of the issue easier and prevent future regressions.

## Contributing to the User Guide
Maybe you are good at explaining how to use the SQL Statement Builder to end users? Help us improve the [user guide](doc/user_guide/user_guide.md)!

## Ideas
Last but not least if you have ideas for ways to improve or extend OFT, feel free to write a [feature request](https://github.com/EXASOL/sql-statement-builder/issues/new?template=Feature_request.md).

# Style Guides
We want the SQL Statement Builder to have a professional and uniform coding style. Formatter rules for Eclipse are part of the project. If you use a different editor, please make sure to match the current code formatting.

We develop the code following the principles described in Robert C. Martin's book "Clean Code" as our guide line for designing and organizing the code.
