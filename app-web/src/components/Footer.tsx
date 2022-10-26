import { css } from '@emotion/react';
import styled from '@emotion/styled';

import { medias, sizes } from '../designSystem';

const PAGE_IDS = {
  terms: '1b83bff221e84c2891cb070236b9690c',
  privacy: '394286eba4ba469fa2091bf6d1065136',
};

const borderStyle = css`
  &:before {
    display: inline-block;
    margin: 0 20px;
    width: 1px;
    height: 16px;
    vertical-align: -2px;
    background: #505050;
    content: '';
  }
`;

const Wrapper = styled.footer({
  fontSize: '.7em',
  padding: '3em 0',
  background: '#1A1A1A',
  color: '#DBDBDB',
  [medias.desktop]: {
    fontSize: '1em',
  },
});

const Container = styled.div({
  margin: '0 auto',
  width: '90%',
  maxWidth: sizes.maxWidth,
});

const Header = styled.div({
  display: 'flex',
  justifyContent: 'space-between',
  marginBottom: '1em',
});

const CompanyInformation = styled.div({
  marginBottom: '2em',
  '& ul': {
    [medias.desktop]: {
      display: 'flex',
      marginBottom: '.5em',
    },
  },
  '& li': {
    [medias.desktop]: {
      '&:nth-of-type(n + 2)': borderStyle,
    },
  },
  '& address': {
    fontStyle: 'normal',
    '& a': {
      color: '#DBDBDB',
    },
  },
});

const Title = styled.h3();

const Navigation = styled.nav({
  '& a': [
    {
      color: '#DBDBDB',
      '&:last-of-type': borderStyle,
    },
  ],
});

const Copyright = styled.div();

export default function Footer() {
  const { terms, privacy } = PAGE_IDS;

  return (
    <Wrapper>
      <Container>
        <Header>
          <Title>코드숨</Title>
          <Navigation>
            <a
              href={`https://www.notion.so/${terms}`}
              target="_blank"
              rel="noreferrer"
            >
              이용약관
            </a>
            <a
              href={`https://www.notion.so/${privacy}`}
              target="_blank"
              rel="noreferrer"
            >
              개인정보 취급방침
            </a>
          </Navigation>
        </Header>
        <CompanyInformation>
          <ul>
            <li>
              대표: 한윤석
            </li>
            <li>
              사업자등록번호: 880-11-00789
            </li>
            <li>
              통신판매업 신고: 2020-경기하남-1521 호
            </li>
          </ul>
          <address>
            <ul>
              <li>
                주소: 서울시 강남구 논현로 79길 15 2층
              </li>
              <li>
                문의메일:
                {' '}
                <a href="mailto:codesoom@gmail.com">
                  codesoom@gmail.com
                </a>
              </li>
            </ul>
          </address>
        </CompanyInformation>
        <Copyright>CodeSoom ⓒ 2020 All Right Reserved.</Copyright>
      </Container>
    </Wrapper>
  );
}
