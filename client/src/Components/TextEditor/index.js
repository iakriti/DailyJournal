import React, { useState, useContext, useEffect } from 'react';
import UserContext from '../../Context/UserContext';

import API from '../../utils/API';
import FormBtn from '../FormBtn';
import Alert from '../Alert';

import { Editor } from 'react-draft-wysiwyg';
import { EditorState, convertToRaw, convertFromRaw } from 'draft-js';

import moment from 'moment';
 
import { Row, Col, Container, Card } from 'react-bootstrap';

import { makeStyles } from '@material-ui/core/styles';
import { TextField } from '@material-ui/core';

import './react-draft-wysiwyg.css';
import './style.css';

const useStyles = makeStyles((theme) => ({
	
	container: {
		display: 'flex',
		flexWrap: 'wrap',
	},

	calendarTextField: {
		width: '100%',
		paddingLeft: '0',
	    margin: '5',
	},

	titleTextField: {
		width: '70ch',
		paddingRight: '0',
		margin: '5',
	},
}));

export default function JournalEntryForm(props) {

	const classes = useStyles();

	const { userData } = useContext(UserContext);

	const editId = props.editId ? props.editId : '';

	let today;

	let maxDate = moment().format('YYYY MM DD').split(' ').join('-');

	let editorContent = EditorState.createEmpty();

	const [title, setTitle] = useState('');
	const [editorState, setEditorState] = useState({
		editorState: editorContent,
	});

	const [date, setDate] = useState();

	const [error, setError] = useState();

	useEffect(() => {
		todayEntryCheck();
	}, []);

	async function todayEntryCheck() {

		if (editId !== '') {
			const editEntryData = await API.getOneJournalEntry(
				editId,
				userData.user.id
			);

			today = editEntryData.data.entryDate.substring(0, 10);
			setDate(today);
		}
		else {
			today = moment().format('YYYY MM DD').split(' ').join('-');
			setDate(today);
		}
		const todaysEntry = await API.checkAJournalEntry(
			today,
			userData.user.id
		);
		if (JSON.stringify(todaysEntry.data) !== '{}') {
			setTitle(todaysEntry.data.title);
			const convertedState = convertFromRaw(
				JSON.parse(todaysEntry.data.body)
			);
			const editorContent = EditorState.createWithContent(
				convertedState
			);
			setEditorState({ editorState: editorContent });
		}
	}



	const onDateChange = async (event) => {
		setTitle('');

		setEditorState({ editorState: EditorState.createEmpty() });
		let dateVal = event.target.value;

		dateVal = dateVal.split(' ').join('-');
		setDate(dateVal);
		const journalEntryCheck = await API.checkAJournalEntry(
			dateVal,
			userData.user.id
		);
		if (JSON.stringify(journalEntryCheck.data) !== '{}') {
			setTitle(journalEntryCheck.data.title);
			const convertedState = convertFromRaw(
				JSON.parse(journalEntryCheck.data.body)
			);

			const editorContent = EditorState.createWithContent(
				convertedState
			);


			setEditorState({ editorState: editorContent });
		}
	};


	const handleEditorChange = (editorState) => {
		setEditorState({ editorState });
	};

	const handleFormSubmit = async (event) => {
		
		event.preventDefault();

		//If the user had not entered title and content in text area
		if (title === '' || editorState === '') return;

		//Check to see if user had already submitted the note for the day
		const journalEntryCheck = await API.checkAJournalEntry(
			date,
			userData.user.id
		);

		//Check to see whether the user aready had an entry for that date
		if (JSON.stringify(journalEntryCheck.data) === '{}') {
			
			// Body for creating a journal Entry
			const journalEntry = {
				title: title,
				body: JSON.stringify(
					convertToRaw(
						editorState.editorState.getCurrentContent()
					)
				),
				entryDate: date,
				userId: userData.user.id,
			};

			// Call API to create jounal entry
			const newEntry = await API.createJournalEntry(journalEntry);

			// Success to show if the Journal entry is posted successfully
			if (JSON.stringify(newEntry) !== {}) {
				setError('Journal Entry Created successfully!!');
			}
		} else {
			
			//body for updating the journal Entry
			const updateEntry = {
				title: title,
				body: JSON.stringify(
					convertToRaw(
						editorState.editorState.getCurrentContent()
					)
				),
			};

			//Fetches the note ID and User Id
			const noteId = journalEntryCheck.data._id;
			const userId = userData.user.id;

			//  API call to update jounal entry if the user had an Entry already for the date
			const updatedEntry = await API.updateOneJournalEntry(
				noteId,
				userId,
				updateEntry
			);

			// show success alert if journal updated successfully
			if (JSON.stringify(updatedEntry) !== {}) {
				setError('Journal Entry Updated!! ');
			}
		}
	};



	// JSX to render the page
	return (
		<div>
			<br />
			<br />
			{error && (
								<Alert
									message={error}
									type="success"
									clearError={() =>
										setError(undefined)
									}
								/>
							)}
			{/* text editor card */}
			<Card className="shadow z-depth-5 card-border my-2 mx-2">
				<Card.Body>
					<Card.Title>
						<div className="text-center">
							<h2 className= "headingText">Enter your Journal Entry</h2>
						</div>
					</Card.Title>
					<br />
					<Card.Text>
						<Container fluid="md">
							<div>
								<Row>
									<Col md={8} s={6} className="pr-2 mb-2">
										<form
											className={
												classes.container
											}
											noValidate
											autoComplete="off"
										>
											{/* Title Field */}
											<TextField
												id="outlined-basic"
												label="Title"
												variant="outlined"
												name="title"
												value={title}
												className={
													classes.titleTextField
												}
												onChange={(event) =>
													setTitle(
														event
															.target
															.value
													)
												}
											/>
										</form>
									</Col>

									<Col md={4} s={6} className="pr-2 mb-2">
										<form
											className={classes.root}
											noValidate
											autoComplete="off"
										>
											{/* Date Picker */}
											<TextField
												id="date"
												value={date}
												label="Date"
												type="date"
												variant="outlined"
												className={
													classes.calendarTextField
												}
												InputProps={{
													inputProps: {
														max: maxDate,
													},
												}}
												onChange={
													onDateChange
												}
												InputLabelProps={{
													shrink: true,
												}}
											/>
										</form>
									</Col>
								</Row>

								<br />

								<div className="form-group">
									
									{/* Text Editor */}
									<Editor
										editorState={
											editorState.editorState
										}
										onEditorStateChange={
											handleEditorChange
										}
										wrapperClassName="wrapper-class"
										editorClassName="editor-class"
										toolbarClassName="toolbar-class"
										className="texteditorclass"
									/>
								</div>

								<div className="text-center">
									
									{/* Submit Button */}
									<FormBtn
										onClick={handleFormSubmit}
									>
										Submit
									</FormBtn>
								</div>
							</div>

							<br />

							{/* Success alert */}
							{/* {error && (
								<Alert
									message={error}
									type="success"
									clearError={() =>
										setError(undefined)
									}
								/>
							)} */}
						</Container>
					</Card.Text>
				</Card.Body>
			</Card>
		</div>
	);
}
