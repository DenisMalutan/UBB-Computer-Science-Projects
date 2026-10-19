% valley(+List)
% A list must have at least 3 elements to form a valley and start by decreasing.
valley([H1, H2 | T]) :-
    H1 > H2,
    valley_dec([H2 | T]).

% valley_dec(+List)
% If the next element is smaller, we continue in the decreasing phase.
valley_dec([H1, H2 | T]) :-
    H1 > H2,
    valley_dec([H2 | T]).

% If the next element is larger, we transition to the increasing phase.
valley_dec([H1, H2 | T]) :-
    H1 < H2,
    valley_inc([H2 | T]).

% valley_inc(+List)
% Base case: We successfully reached the last element while increasing.
valley_inc([_]).

% If the next element is larger, we continue in the increasing phase.
valley_inc([H1, H2 | T]) :-
    H1 < H2,
    valley_inc([H2 | T]).



% alt_sum(+List, -Result)
% Base case: an empty list evaluates to 0.
alt_sum([], 0).

% We add the head, and call alt_diff to subtract the next element.
alt_sum([H|T], Result) :-
    alt_diff(T, Rest),
    Result is H + Rest.

% alt_diff(+List, -Result)
alt_diff([], 0).

% We subtract the head, and call alt_sum to add the next element.
alt_diff([H|T], Result) :-
    alt_sum(T, Rest),
    Result is -H + Rest.