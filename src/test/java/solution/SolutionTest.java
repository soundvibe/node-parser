package solution;

import javaslang.control.Validation;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static javaslang.control.Validation.*;
import static org.junit.Assert.assertEquals;

/**
 * @author OZY on 2016.11.11.
 */
public class SolutionTest {

    @Test
    public void shouldReturnFailure_NodesAreNull() throws Exception {
        final Validation<ErrorType, String> actual = Solution.parse(null);
        assertEquals(invalid(ErrorType.NODES_ARE_NULL), actual);
    }

    @Test
    public void shouldParseOneNode() throws Exception {
        final Validation<ErrorType, String> actual = Solution.parse("(A,B)");
        assertEquals(valid("(A(B))"), actual);
    }

    @Test
    public void shouldParseTwoNodeWithSameHead() throws Exception {
        final Validation<ErrorType, String> actual = Solution.parse("(A,B) (A,C)");
        assertEquals(valid("(A(B)(C))"), actual);
    }

    @Test
    public void shouldParseThreeNodesAndTwoHeads() throws Exception {
        final Validation<ErrorType, String> actual = Solution.parse("(A,B) (A,C) (B,D)");
        assertEquals(valid("(A(B(D))(C))"), actual);
    }

    @Test
    public void shouldParseSuppliedNodes() throws Exception {
        final Validation<ErrorType, String> actual = Solution.parse("(A,B) (A,C) (B,G) (C,H) (E,F) (B,D) (C,E)");
        assertEquals(valid("(A(B(D)(G))(C(E(F))(H)))"), actual);
    }

    @Test
    public void shouldReturnFailureWhenTwoManySameNodes() throws Exception {
        final Validation<ErrorType, String> actual = Solution.parse("(A,B) (A,C) (B,G) (C,H) (E,F) (B,D) (C,E) (A,D)");
        assertEquals(invalid(ErrorType.MORE_THAN_TWO_CHILDREN), actual);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldThrowWhenTwoManySameNodes() throws Exception {
        expectedEx.expect(NodeParserError.class);
        expectedEx.expectMessage("MORE_THAN_TWO_CHILDREN");

        Solution.SExpression("(A,B) (A,C) (B,G) (C,H) (E,F) (B,D) (C,E) (A,D)");
    }

    @Test
    public void shouldParseHead() throws Exception {
        final Character actual = Solution.parseHead("(A,B)");
        assertEquals('A', actual.charValue());
    }


    @Test
    public void shouldParseHeadWhenSpaceInBetween() throws Exception {
        final Character actual = Solution.parseHead("(A , B)");
        assertEquals('A', actual.charValue());
    }

    @Test
    public void shouldParseTail() throws Exception {
        final Character actual = Solution.parseTail("(A,B)");
        assertEquals('B', actual.charValue());
    }

}