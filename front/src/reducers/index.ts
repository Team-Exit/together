import * as types from '../actions/example/ActionTypes';

const initialState = {
    state: 0
};

function example(state = initialState, action: any) {
    switch (action.type) {
        case types.EXAMPLE:
            return {
                ...state
            };
        default:
            return state;
    }
}

export default example;