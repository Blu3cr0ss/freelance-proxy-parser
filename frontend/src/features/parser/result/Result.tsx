import { Button } from '@mui/material';
import Paper, { PaperProps } from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { FC } from 'react';

const ProxyFlux: {
  ip: string;
  fraudScore: number;
  fakeIp: string;
}[] = [
  {
    ip: '95.143.239.145',
    fraudScore: 0,
    fakeIp:
      'node-de-32.astroproxy.com:10455/api/changeIP?apiToken=49fbea178cdf4dc0',
  },
  {
    ip: '84.115.237.116',
    fraudScore: 0,
    fakeIp: 'mydearfriend3357:2431cf@162.19.196.77:10247',
  },
  {
    ip: '83.78.12.73',
    fraudScore: 0,
    fakeIp:
      'node-de-63.astroproxy.com:10707/api/changeIP?apiToken=49fbea178cdf4dc0',
  },
  {
    ip: '178.190.184.243',
    fraudScore: 0,
    fakeIp:
      'node-de-96.astroproxy.com:10653/api/changeIP?apiToken=49fbea178cdf4dc0',
  },
  {
    ip: '194.166.184.74',
    fraudScore: 0,
    fakeIp: 'mydearfriend3357:2431cf@5.9.244.199:10653',
  },
  {
    ip: '193.83.240.230',
    fraudScore: 0,
    fakeIp:
      'node-de-42.astroproxy.com:10707/api/changeIP?apiToken=49fbea178cdf4dc0',
  },
];

export const Result: FC<PaperProps> = ({ sx }) => {
  const rows = ProxyFlux;
  return (
    <TableContainer component={Paper} sx={sx}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Proxy</TableCell>
            <TableCell align="right">IP</TableCell>
            <TableCell align="right">Статус</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow
              key={row.ip}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.fakeIp}
              </TableCell>
              <TableCell align="right">{row.ip}</TableCell>
              <TableCell align="right">
                <Button variant="contained">Сменить&nbsp;IP</Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};
