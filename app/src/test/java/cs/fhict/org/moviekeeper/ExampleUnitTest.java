package cs.fhict.org.moviekeeper;

import org.junit.Test;

import cs.fhict.org.moviekeeper.data.MoviesRepository;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getMoviesByTitle_Works() {
        MoviesRepository moviesRepository = new MoviesRepository(movieRemote);

        moviesRepository.getMoviesByTitle("John");


    }
}