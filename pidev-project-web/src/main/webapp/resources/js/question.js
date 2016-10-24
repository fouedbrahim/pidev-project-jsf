  (function () {
        var questions = [{
            question: "Approximately how many years ago did plastic surgery begin?",
            choices:
               [3000, 1000, 4000, 6000, 8000],
            correctAnswer: 2
        }, {
            question: "Who was the first major American surgeon to perform plastic surgery?",
            choices: ["Benjamin Franklin", "Albert Einstein", "John Peter", "Tom Feller", "John Mettauer"],
            correctAnswer: 4
        }, {
            question: "Which of the following is not a type of plastic surgery?",
            choices: ["Knee Replacement", "Botox", "Laser Hair Removal", "Face Lift", "No one"],
            correctAnswer: 0
        }, {
            question: "Which procedure is the most expensive out of the following procedures?",
            choices: ["Lipuisaction", "Dermabrasion", "Facelift", "Lower Body Lift", "Liposuction"],
            correctAnswer: 3
        }, {
            question: "This is not a question,Can you Rate our services ???",
            choices: [1, 2, 3, 4, 5],
            correctAnswer: 4
        }];

        var questionCounter = 0; //Tracks question number
        var selections = []; //Array containing user choices
        var quiz = $('#quiz'); //Quiz div object

        // Display initial question
        displayNext();

        // Click handler for the 'next' button
        $('#next').on('click', function (e) {
            e.preventDefault();

            // Suspend click listener during fade animation
            if (quiz.is(':animated')) {
                return false;
            }
            choose();

            // If no user selection, progress is stopped
            if (isNaN(selections[questionCounter])) {
                alert('Please make a selection!');
            } else {
                questionCounter++;
                displayNext();
            }
        });

        // Click handler for the 'prev' button
        $('#prev').on('click', function (e) {
            e.preventDefault();

            if (quiz.is(':animated')) {
                return false;
            }
            choose();
            questionCounter--;
            displayNext();
        });

        // Click handler for the 'Start Over' button
        $('#start').on('click', function (e) {
            e.preventDefault();

            if (quiz.is(':animated')) {
                return false;
            }
            questionCounter = 0;
            selections = [];
            displayNext();
            $('#start').hide();
        });

        // Animates buttons on hover
        $('.button').on('mouseenter', function () {
            $(this).addClass('active');
        });
        $('.button').on('mouseleave', function () {
            $(this).removeClass('active');
        });

        // Creates and returns the div that contains the questions and
        // the answer selections
        function createQuestionElement(index) {
            var qElement = $('<div>', {
                id: 'question'
            });

            var header = $('<h2>Question ' + (index + 1) + ':</h2>');
            qElement.append(header);

            var question = $('<p>').append(questions[index].question);
            qElement.append(question);

            var radioButtons = createRadios(index);
            qElement.append(radioButtons);

            return qElement;
        }

        // Creates a list of the answer choices as radio inputs
        function createRadios(index) {
            var radioList = $('<ul>');
            var item;
            var input = '';
            for (var i = 0; i < questions[index].choices.length; i++) {
                item = $('<li>');
                input = '<input type="radio" name="answer" value=' + i + ' />';
                input += questions[index].choices[i];
                item.append(input);
                radioList.append(item);
            }
            return radioList;
        }

        // Reads the user selection and pushes the value to an array
        function choose() {
            selections[questionCounter] = +$('input[name="answer"]:checked').val();
        }

        // Displays next requested element
        function displayNext() {
            quiz.fadeOut(function () {
                $('#question').remove();

                if (questionCounter < questions.length) {
                    var nextQuestion = createQuestionElement(questionCounter);
                    quiz.append(nextQuestion).fadeIn();
                    if (!(isNaN(selections[questionCounter]))) {
                        $('input[value=' + selections[questionCounter] + ']').prop('checked', true);
                    }

                    // Controls display of 'prev' button
                    if (questionCounter === 1) {
                        $('#prev').show();
                    } else if (questionCounter === 0) {

                        $('#prev').hide();
                        $('#next').show();
                    }
                } else {
                    var scoreElem = displayScore();
                    quiz.append(scoreElem).fadeIn();
                    $('#next').hide();
                    $('#prev').hide();
                    $('#start').show();
                }
            });
        }

        // Computes score and returns a paragraph element to be displayed
        function displayScore() {
            var score = $('<p>', { id: 'question' });

            var numCorrect = 0;
            for (var i = 0; i < selections.length; i++) {
                if (selections[i] === questions[i].correctAnswer) {
                    numCorrect++;
                }
            }

            score.append('You got ' + numCorrect + ' questions out of ' +
                         questions.length + ' right!!!');
            return score;
        }
    })();

