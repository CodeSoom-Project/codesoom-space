import styled from '@emotion/styled';

interface Props {
  columns: number
}

const COURSE_SPACING = '1.6em';

const Column = styled.li(({ columns }: Props) => ({
  width: `calc(
      (100% - ${COURSE_SPACING} * ${columns - 1})
      / ${columns}
    )`,
  [`&:nth-of-type(${columns}n)`]: {
    marginRight: 0,
  },

  '@media (max-width: 767px)': {
    width: '100%',
    marginBottom: '1em',
  },
}));

export default Column;
