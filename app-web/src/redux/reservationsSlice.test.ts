import reducer, {
  initialState,
  toggleReservationsModal,
  toggleRetrospectModal,
} from './reservationsSlice';

describe('toggleReservationsModal', () => {
  it('isOpenReservationsModal을 반대로 변경한다', () => {
    const state = reducer(initialState, toggleReservationsModal());

    expect(state.isOpenReservationsModal).toBe(!initialState.isOpenReservationsModal);
  });
});

describe('toggleRetrospectModal', () => {
  it('isOpenRetrospectModal을 반대로 변경한다', () => {
    const state = reducer(initialState, toggleRetrospectModal());

    expect(state.isOpenRetrospectModal).toBe(!initialState.isOpenRetrospectModal);
  });
});



