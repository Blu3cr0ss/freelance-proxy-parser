import { Typography } from '@mui/material';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import ModalMUI from '@mui/material/Modal';
import { FC } from 'react';
import { BlackListType } from './api';

const style = {
  position: 'absolute' as 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
  height: '80vh',
  overflowY: 'scroll',
};

type Props = {
  open: boolean;
  setBlackList: React.Dispatch<React.SetStateAction<BlackListType | null>>;
  blackList?: BlackListType | null;
};

export const Modal: FC<Props> = ({ blackList, open, setBlackList }) => {
  if (!blackList) {
    return null;
  }
  const handleClose = () => setBlackList(null);

  return (
    <ModalMUI
      open={open}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        <Typography variant="h5" align="center" mb={2} fontWeight={600}>
          Список: {blackList.name}
        </Typography>
        {blackList.ipString.split('\n').map((ip, i) => {
          return (
            <Box
              key={ip + i}
              sx={{
                display: 'flex',
                m: 1,
                alignItems: 'center',
                justifyContent: 'space-evenly',
              }}
            >
              <Typography width={170}>{ip}</Typography>
              <Button variant="contained">Удалить</Button>
            </Box>
          );
        })}
      </Box>
    </ModalMUI>
  );
};
