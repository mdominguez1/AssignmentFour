-- use \c holliday to connect to the holliday database beforehand

-- university database; used in exercises 3.11 and 3.12
-- copying data into the table using \copy; \copy, unlike copy 
-- does not require the user to be superuser
-- @author Mark Holliday
-- @version 19 January 2016

\copy classroom from 'university_db_data/sks_classroom_data.txt';

\copy department from 'university_db_data/sks_department_data.txt';

\copy course from 'university_db_data/sks_course_data.txt';

\copy instructor from 'university_db_data/sks_instructor_data.txt';

\copy section from 'university_db_data/sks_section_data.txt';

\copy teaches from 'university_db_data/sks_teaches_data.txt';

\copy student from 'university_db_data/sks_student_data.txt';

\copy takes from 'university_db_data/sks_takes_data.txt';

\copy advisor from 'university_db_data/sks_advisor_data.txt';

\copy time_slot from 'university_db_data/sks_time_slot_data.txt';

\copy prereq from 'university_db_data/sks_prereq_data.txt';













