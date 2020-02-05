package logic.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Alert.AlertType;
import logic.Main;
import logic.bean.CityDateBean;
import logic.bean.HotelBean;
import logic.model.BookHotelController;
import logic.view.BookHotelListView;

/**
 * 
 * @author metal
 *
 *         MVC controller of the view BookHotelListView.
 */
public class BookHotelListViewController extends MainViewController {

	/**
	 * Reference to the view.
	 */
	private BookHotelListView bookHotelListView;

	/**
	 * Reference to the bean, that will contain the input of the user.
	 */
	private CityDateBean fields;

	/**
	 * Constructor of the class. It initialize the state of the object and
	 * initialize the view with the data of the model.
	 * 
	 * @param view   the view.
	 * @param model  the model.
	 * @param fields bean, input of the user.
	 */
	public BookHotelListViewController(BookHotelListView view, BookHotelController model, CityDateBean fields) {
		super(view, model);

		this.bookHotelListView = (BookHotelListView) super.view;
		this.fields = fields;

		/* Add handlers to buttons. */
		this.bookHotelListView.addSearchListener(new SearchHandler());
		this.bookHotelListView.addMinusHanlder(new MinusHandler());
		this.bookHotelListView.addPlusHanlder(new PlusHandler());

		/* Set the text fields with the input provided by the user. */
		this.bookHotelListView.setCityField(this.fields.getCity());
		this.bookHotelListView.setCheckInDate(this.fields.getCheckIn());
		this.bookHotelListView.setCheckOutDate(this.fields.getCheckOut());
		this.bookHotelListView.setPersonCountText(String.valueOf(this.fields.getPersonCount()));

		if (Integer.parseInt(this.bookHotelListView.getPersonCount()) == 0)

			/* The minus button has to be disabled */
			this.bookHotelListView.disableMinusButton();

		List<HotelBean> hotels = new ArrayList<>();
		hotels = this.model.retrieveHotelByCity(this.fields.getCity());

		if (hotels.isEmpty())

			/* The input of the doesn't produce result. */
			this.bookHotelListView.resultNotFound();

		else

			/* Set the data found to the view. */
			this.bookHotelListView.populateView(hotels, new MoreInformationHandler());

	}

	/**
	 * 
	 * @author metal
	 *
	 *         This class implements the EventHandler interface providing the handle
	 *         method for minus button.
	 */
	private class MinusHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			int personCount = Integer.parseInt(bookHotelListView.getPersonCount());
			personCount--;
			if (personCount == 0)
				bookHotelListView.disableMinusButton();
			bookHotelListView.setPersonCountText(String.valueOf(personCount));

		}

	}

	/**
	 * 
	 * @author metal
	 *
	 *         This class implements the EventHandler interface providing the handle
	 *         method for plus button.
	 */
	private class PlusHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			int personCount = Integer.parseInt(bookHotelListView.getPersonCount());
			personCount++;
			bookHotelListView.enableMinusButton();
			bookHotelListView.setPersonCountText(String.valueOf(personCount));

		}

	}

	/**
	 *
	 * @author metal
	 *
	 *         This class implements the EventHandler interface providing the
	 *         handler method for search button.
	 */
	private class SearchHandler implements EventHandler<ActionEvent> {

		private boolean fieldsAreFilled() {
			return !bookHotelListView.getCityField().isEmpty() && bookHotelListView.getCheckInDate() != null
					&& bookHotelListView.getCheckOutDate() != null
					&& Integer.parseInt(bookHotelListView.getPersonCount()) != 0;
		}

		private boolean checkInDateIsBeforeCheckOutDate() {
			return bookHotelListView.getCheckInDate().isBefore(bookHotelListView.getCheckOutDate())
					|| bookHotelListView.getCheckInDate().equals(bookHotelListView.getCheckOutDate());

		}

		private void checkEmptyFields() {
			if (bookHotelListView.getCityField().isEmpty())
				bookHotelListView.setVisibleErrCityField(true);

			if (bookHotelListView.getCheckInDate() == null)
				bookHotelListView.setVisibleErrCheckInField(true);

			if (bookHotelListView.getCheckOutDate() == null)
				bookHotelListView.setVisibleErrCheckOutField(true);

			if (Integer.parseInt(bookHotelListView.getPersonCount()) == 0)
				bookHotelListView.setVisibleErrPersonCount(true);
		}

		@Override
		public void handle(ActionEvent event) {

			/* Set invisible all errors. */
			bookHotelListView.setVisibleErrCityField(false);
			bookHotelListView.setVisibleErrCheckInField(false);
			bookHotelListView.setVisibleErrCheckOutField(false);
			bookHotelListView.setVisibleErrPersonCount(false);

			if (this.fieldsAreFilled()) {

				/* The user filled all the fields. */
				if (this.checkInDateIsBeforeCheckOutDate()) {

					/* The check-in date is before or equal to the check-out date. */

					/* Fill the bean fields. */
					fields.setCity(bookHotelListView.getCityField());
					fields.setCheckIn(bookHotelListView.getCheckInDate());
					fields.setCheckOut(bookHotelListView.getCheckOutDate());
					fields.setPersonCount(Integer.parseInt(bookHotelListView.getPersonCount()));

					List<HotelBean> rentablePlaces = model.retrieveHotelByCity(fields.getCity());

					if (rentablePlaces.isEmpty())

						/* The input provided by the user doesn't provide result */
						bookHotelListView.resultNotFound();

					else

						bookHotelListView.populateView(rentablePlaces, new MoreInformationHandler());

				} else {

					/* The Check-Out date is before the Check-In date. */
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText(null);
					alert.setContentText("Check-Out cannot be before Check-In date.");

					alert.showAndWait();

				}

			} else {

				/* The user doesn't fill all the fields. */
				this.checkEmptyFields();

			}

		}

	}

	/**
	 * 
	 * @author metal
	 *
	 *         This class implements the EventHandler interface providing the handle
	 *         for the MoreinformationButton.
	 */
	public class MoreInformationHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {

			/* Fill the bean with the data provided by the user. */
			fields.setCity(bookHotelListView.getCityField());
			fields.setCheckIn(bookHotelListView.getCheckInDate());
			fields.setCheckOut(bookHotelListView.getCheckOutDate());
			fields.setPersonCount(Integer.parseInt(bookHotelListView.getPersonCount()));

			int id = Integer.parseInt(((Control) event.getSource()).getId()); // The id of the hotel selected.

			try {

				/* Change the view to HotelView and initialize the new controller. */
				Main.getInstance().changeToHotelView();
				new HotelViewController(Main.getInstance().getHotelView(), model.getRentablePlace(id), fields);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
