import { FC, } from 'react';
import { Container, Content, LinkContainer } from './styles';
import SidebarItem from '../SidebarItem';
import {
  FaTimes,
  FaHome,
} from 'react-icons/fa';

interface SidebarProps {
  sidebar: boolean;
  setSidebar: (isActive: boolean) => void;
}

const Sidebar: FC<SidebarProps> = ({ sidebar, setSidebar }) => {

  const closeSidebar = () => {
    setSidebar(false);
  };

  return (
    <Container sidebar={sidebar}>
      <FaTimes onClick={closeSidebar} />
      <Content onClick={closeSidebar}>
        <LinkContainer to="/">
          <SidebarItem Icon={FaHome} Text="Home" />
        </LinkContainer>
      </Content>
    </Container>
  );
};

export default Sidebar;