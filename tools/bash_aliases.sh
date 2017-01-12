#!/usr/bin/env bash

# Helpful aliases for developing bonappetit.
# Expects this project to sit in /m/codebase/bonappetit-server.

SERVER_BASE=/m/codebase/bonappetit-server

# documentation
alias doc="${SERVER_BASE}/gradlew publishGhPages"

# git
alias g="git"
alias gs="git status"
alias gf="git fetch"
alias gpl="git pull"
alias gp="git push"
alias gc="git commit"
alias gca="git commit --amend"
alias gm="git merge"
alias gco="git checkout"