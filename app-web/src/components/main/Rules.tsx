import styled from '@emotion/styled';

import rules from '../../data/rules';
import Column from './Column';

interface Props {
  rule: {
    name: string,
    title: string,
    description: string
  }
}

interface Rule1 {
  name: string;
  title: string;
  description: string
}

const Wrapper = styled.div({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'flex-start',
  alignItems: 'center',
  marginLeft: 'calc((100% - 100vw) / 2)',
  marginRight: 'calc((100% - 100vw) / 2)',
  padding: '3.3em 0',

  '@media (max-width: 767px)': {
    padding: '5%',
  },
});

const Columns = styled.ul({
  display: 'flex',
  flexWrap: 'wrap',
  justifyContent: 'space-between',
  width: '800px',
  '@media (max-width: 767px)': {
    display: 'unset',
    width: '100%',
  },
});

const Image = styled.img({
  display: 'block',
  width: '100%',
});

const Card = styled.div({
  display: 'flex',
  flexDirection: 'column',
  height: '100%',
  minHeight: '5em',
  border: '1px solid #f2f2f2',
});

const Content = styled.div({
  flex: 1,
  padding: '1.5em',
});

const RuleTitle = styled.h3({
  fontSize: '1.2em',
});

const Title = styled.h2({
  fontSize: '1.6em',
  textAlign: 'center',
  marginBottom: '1em',
  '& span': {
    display: 'block',
    fontSize: '.5em',
    fontWeight: 'normal',
    lineHeight: '1.6',
    color: '#c2c2c2',
  },
});

const Description = styled.div({
  fontSize: '.8m',
  lineHeight: '1.3',
  marginTop: '1em',
});

function Rule({ rule }: Props) {
  const { name, title, description } = rule;

  return (
    <Card>
      <Image src={name} alt={title}/>
      <Content>
        <RuleTitle>
          {title}
        </RuleTitle>
        <Description>
          {description}
        </Description>
      </Content>
    </Card>
  );
}

export default function Rules() {
  return (
    <Wrapper>
      <Title><span>CodeSoom Space Rules</span>공부방 규칙</Title>
      <Columns>
        {rules.map((rule: Rule1) => (
          <Column
            key={rule.title}
            columns={2}
          >
            <Rule
              rule={rule}
            />
          </Column>
        ))}
      </Columns>
    </Wrapper>
  );
}
