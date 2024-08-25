const form = document.getElementById('api-form');
const responseDiv = document.getElementById('api-response');

form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const userId = document.getElementById('user_id').value;
  const collegeEmailId = document.getElementById('college_email_id').value;
  const collegeRollNumber = document.getElementById('college_roll_number').value;
  const numbers = document.getElementById('numbers').value;
  const alphabets = document.getElementById('alphabets').value;

  try {
    const response = await fetch('/api/endpoint', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ userId, collegeEmailId, collegeRollNumber, numbers, alphabets }),
    });

    const data = await response.json();

    responseDiv.innerHTML = `
      <h2>API Response</h2>
      <p>Status: ${data.status}</p>
      <p>User ID: ${data.user_id}</p>
      <p>College Email ID: ${data.college_email_id}</p>
      <p>College Roll Number: ${data.college_roll_number}</p>
      <p>Numbers: ${data.numbers}</p>
      <p>Alphabets: ${data.alphabets}</p>
      <p>Highest Lowercase Alphabet: ${data.highest_lowercase_alphabet}</p>
    `;
  } catch (error) {
    responseDiv.innerHTML = `<p>Error: ${error.message}</p>`;
  }
});
