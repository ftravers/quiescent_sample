# quie

A 'hello world' for quiescent.

## Setup

Demonstrates simple 2-way data binding.

Run

    % lein figwheel

Load: http://localhost:3449

Launch clj(s) REPLs

In cljs REPL do:

    @world

and

    (swap! world assoc :text "abc")

to show updating data from front or back-end.

## License

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
