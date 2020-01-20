package logic.model.bookingstate;

import logic.bean.BookingBean;
import logic.model.Booking;
import logic.model.dao.BookingDAOImpl;

/**
 * 
 * @author metal
 *
 * This class represent the accepted state. A booking can be
 * accepted by the owner after its submit by the user.
 */
public class AcceptedState implements BookingState{

	@Override
	public void accept(Booking context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Booking context) {
		
		context.setState(new DeletedState());
		
		BookingBean bean = new BookingBean();
		bean.setCheckIn(context.getCheckIn());
		bean.setCheckOut(context.getCheckOut());
		bean.setState(StateEnum.DELETED);
		
		new BookingDAOImpl().updateBooking(bean);
		
	}

	@Override
	public void resubmit(Booking context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StateEnum getEnumValueOfState() {
		return StateEnum.ACCEPTED;
	}

}
