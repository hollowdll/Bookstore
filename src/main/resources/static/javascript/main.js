// Confirm deletion of a book entity
const confirmDeletion = (bookId, bookAuthor, bookTitle) => {
	const confirm = window.confirm(
`Do you really want to delete this book?
ID: ${bookId}
Author: ${bookAuthor}
Title: ${bookTitle}`
	);
	
	if (confirm) {
		window.location = `/booklist/delete/${bookId}`;
	}
}