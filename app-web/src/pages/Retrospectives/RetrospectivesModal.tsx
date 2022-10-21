import { useDispatch } from 'react-redux';

import { useAppSelector } from '../../hooks';

import { get } from '../../utils';

import {
  saveIsDetailRetrospectives,
  saveIsUpdateRetrospectives,
  saveRetrospectives,
} from '../../redux/retrospectivesSlice';

import { useQuery } from 'react-query';

import { getRetrospective, retrospectivesKeys } from '../../services/retrospectives';

import styled from '@emotion/styled';

import { Button, CircularProgress, Dialog, DialogTitle, TextField } from '@mui/material';

const Wrap = styled.div({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'space-around',
  padding: '1.5rem',
});

const ButtonWrap = styled.div({
  display: 'flex',
  justifyContent: 'flex-end',

  'button: nth-of-type(1)': {
    marginRight: '1rem',
  },
});

const Title = styled(DialogTitle)({
  marginBottom: '0',
  paddingBottom: '0',
});

const Text = styled(TextField)({
  margin: '1rem 3rem 1rem 0',
});

const TextTitle = styled.h1({
  marginBottom: '1rem',
  fontSize: '1.3rem',
});

const TextBox = styled.div({
  display: 'flex',
  flexDirection: 'column',
  maxHeight: '20rem',
  overflow: 'auto',
  marginBottom: '2rem',

  'p': {
    margin: '0 0 1rem 0',
  },
});

interface Props {
  open: boolean,
  onClose: React.ReactEventHandler,
  onApply: React.ReactEventHandler,
  onUpdate: React.ReactEventHandler,
}

function DetailRetrospectivesDialog({ onClose }: { onClose: React.ReactEventHandler }) {
  const dispatch = useDispatch();

  const { id } = useAppSelector(get('reservations'));

  const { isLoading, data } = useQuery(
    retrospectivesKeys.retrospectivesById(id),
    () => getRetrospective(id), {
      onSuccess: (response) => {
        dispatch(saveRetrospectives(response.content));
      },
    },
  );

  const onClickUpdate = () => {
    dispatch(saveIsDetailRetrospectives(false));
    dispatch(saveIsUpdateRetrospectives(true));
  };

  if (isLoading) {
    return <CircularProgress />;
  }

  const { content } = data;

  return (
    <Wrap>
      <TextTitle>회고</TextTitle>
      <TextBox>{content.split('\n').map((line: string) => (<p key={id}>{line}</p>))}</TextBox>
      <ButtonWrap>
        <Button variant="outlined" size="small" onClick={onClose}>
          취소
        </Button>
        <Button variant="outlined" size="small" onClick={onClickUpdate}>
          수정
        </Button>
      </ButtonWrap>
    </Wrap>
  );
}

function ApplyRetrospectivesDialog({ onClose, onApply, onUpdate }: {
  onClose: React.ReactEventHandler;
  onApply: React.ReactEventHandler;
  onUpdate: React.ReactEventHandler;
}) {
  const dispatch = useDispatch();

  const { retrospectives, isUpdate } = useAppSelector(get('retrospectives'));

  const characterMinimum = 100;
  const characterMaximum = 1000;

  const isMinimum = retrospectives.length > characterMinimum;

  const handleChange: React.ChangeEventHandler<HTMLInputElement> = (event) => {
    dispatch(saveRetrospectives(event.target.value));
  };

  return (
    <>
      <Title>
        회고 작성하기
      </Title>
      <Wrap>
        <Text
          inputProps={{ maxLength: characterMaximum, minLength: characterMinimum }}
          placeholder='회고를 100자 이상 작성해 주세요'
          helperText={`${retrospectives.length} /${characterMaximum}`}
          value={retrospectives}
          onChange={handleChange}
          variant='outlined'
          fullWidth
          multiline
          rows={3}
        />
        <ButtonWrap>
          <Button
            variant='outlined'
            size='small'
            onClick={onClose}
          >
            취소
          </Button>
          {isUpdate
            ? (
              <Button
                variant='contained' size='small'
                disabled={!isMinimum}
                onClick={onUpdate}
              >
                수정
              </Button>
            )
            : (
              <Button
                variant='contained' size='small'
                disabled={!isMinimum}
                onClick={onApply}
              >
                제출
              </Button>
            )}
        </ButtonWrap>
      </Wrap >
    </>
  );
}

export default function RetrospectivesModal({ open, onClose, onApply, onUpdate }: Props) {
  const { isDetail } = useAppSelector(get('retrospectives'));

  return (
    <Dialog
      open={open}
      onClose={onClose}
      aria-labelledby="form-dialog-title"
    >
      {isDetail
        ? <DetailRetrospectivesDialog onClose={onClose} />
        : <ApplyRetrospectivesDialog onClose={onClose} onApply={onApply} onUpdate={onUpdate} />}
    </Dialog >
  );
}
