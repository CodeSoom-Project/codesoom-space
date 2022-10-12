import reducer, {
  initialState,
  toggleReservationsModal,
} from './reservationsSlice';

describe('toggleReservationsModal', () => {
  it('isOpenReservationsModal을 반대로 변경한다', () => {
    const state = reducer(initialState, toggleReservationsModal());

    expect(state.isOpenReservationsModal).toBe(!initialState.isOpenReservationsModal);
  });
});

