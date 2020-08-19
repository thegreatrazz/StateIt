# StateIt

StateIt is a state machine modelling thing. I couldn't find anything I liked to use. Umple (the command line tool, not the crummy website) doesn't generate graphics, and I didn't find anything that could deal with nested states. So I made my own.

It takes in source files like this:

```
machine Game {
    state Title {
    }
}
``` 

And generates pretty graphics like this:

## Crash Course

> Note that this crash course contains comments.
> StateIt doesn't. They act purely as placeholders.

The source file contains **machines**. **Machines** contain **states** and **state machines** (states that are machines).

```
machine Chest {
    state Open {};
    machine Closed {
        state Unlocked {};
        state Locked {};
    };
};
```

**States** contain **transitions**. **Transitions** specify an **action**, and a **state**.

```
state Open {
    close > Closed;
    lock > Closed:Lock;
};
```

Since states and machines can be nested, scope rules apply. A transition can use any parent state.

To transition to a nested state, `:` could be used. If a nested state shadows a parent state, `_` acts as a global machine.

Look in the [examples](examples) folder for more examples.
