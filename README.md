# Sky Betting & Gaming Technical Test

## Requirements

 Create a server-side form handler in the language of your choice, to load and save data from our supplied HTML form.

1. Functionality required:
    * Simple UPDATE capability against the existing RECORDS.
    * Other CRUD functions could be added if you have time.
2. Validation:
    * Test coverage.
3. Datastore:
    * Text file.

## Tech choices & Reasoning

For the back end, I have mainly used Java + Spring Boot technology. There are of course other multiple small libraries used behind the scenes like Lombok, GSON and etc to help with faster code development.

For the front end I have chosen to use Angular 2 > (5.2 to be precise) with other libraries which you can check out in [package.json](https://github.com/firebotQL/tech-test/blob/master/front-end/package.json)
 file.

I could have used Node.js and Express for the backend and BackboneJS for front-end, with which I was comfortable at some point. Or any other trending tech, but because predominantly recently I was using chosen technologies at my current workplace so I bluntly decided that this would be the fastest way to develop for me at the minute.

I have also tried to follow all the principles you have mentioned such as: security, performance, readability, testability, scalability, simplicity
I can comment on all above on demand.

## How to/Usage

Make sure you are in the root of the repository in your terminal.

From the terminal being in 'back-end' directory run:
```sh
./gradlew bootRun
```

From the terminal being in 'front-end' directory run:
```sh
npm install -g @angular/cli && npm install && ng serve --open
```

NOTE:
*If browser didn't open automatically please go to http://localhost:4200 as this URL serves you the app*
