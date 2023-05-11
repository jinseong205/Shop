import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom'

function Header() {
  return (
    <>
      <Navbar bg="dark" expand="lg">
        <Container fluid>
          <Link to="/" className="navbar-brand me-5 text-light">JShop</Link>
          <Navbar.Toggle className="bg-light" aria-controls="navbarScroll" />
          <Navbar.Collapse id="navbarScroll">

            <Nav
              className="container-fluid my-2 my-lg-0"
              style={{ maxHeight: '100px' }}
              navbarScroll
            >

              <Form className="d-flex  me-auto" style={{minWidth:"40%"}}>
                <Form.Control
                  type="search"
                  placeholder="Search"
                  className="me-2"
                  aria-label="Search"
                />
                <Button className="text-nowrap" variant="outline-light">
                  검색
                </Button>
              </Form>

              <div className="d-flex">
                <Link className="nav-link text-light text-nowrap" to="/">BEST</Link>
              </div>
              <div className="d-flex">
                <Link className="nav-link text-light text-nowrap" to="/">전체</Link>
              </div>
              <div className="d-flex">
                <Link className="nav-link text-light text-nowrap" to="/">공지사항</Link>
              </div>

            </Nav>

          </Navbar.Collapse>
        </Container>
      </Navbar>
      <Navbar variant="outline-dark" >
        <Container fluid >

          <div className="d-flex me-auto"></div>
          <div className="d-flex me-4">
            <Link className="nav-link" to="/join">회원가입</Link>
          </div>
          <div className="d-flex me-4">
            <Link className="nav-link" to="/login">로그인</Link>
          </div>
        </Container>
      </Navbar>
    </>
  );
}

export default Header;